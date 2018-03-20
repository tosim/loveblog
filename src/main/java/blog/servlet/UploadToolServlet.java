package blog.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.entity.Tool;
import blog.service.ToolService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

@WebServlet("/UploadToolServlet")
public class UploadToolServlet extends AutowiredHttpServlet {
    @Autowired
    ToolService toolService;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String rootPath = this.getServletContext().getRealPath("/");
        System.out.println("rootPath:"+rootPath);
        String tempPath = rootPath + "tools\\temp";
        File tmpFile = new File(tempPath);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }

        JSONObject json = new JSONObject();
        Tool tool = new Tool();
        try{
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024*500);//设置缓冲区的大小为500KB，如果不指定，那么缓冲区的大小默认是10KB
            factory.setRepository(tmpFile);
            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setHeaderEncoding("UTF-8");
            if(!ServletFileUpload.isMultipartContent(request)){
                return;
            }
            upload.setFileSizeMax(1024*1024*500);
            upload.setSizeMax(1024*1024*1024*2);
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                if(item.isFormField()){
                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");
                    System.out.println(name + "=" + value);
//                    json.put(name,value);
                    if(name.equals("toolname")){
                        tool.setT_name(value);
                    }else if(name.equals("toolversion")){
                        tool.setT_version(value);
                    }else if(name.equals("toolowner")){
                        tool.setT_owner(value);
                    }else if(name.equals("toolabstract")){
                        tool.setT_abstract(value);
                    }else if(name.equals("tooldetail")){
                        tool.setT_description(value);
                    }
                }else{
                    String filename = item.getName();
                    System.out.println(filename);
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
                    System.out.println("上传的文件的扩展名是："+fileExtName);
                    InputStream in = item.getInputStream();
                    String saveFilename = makeFileName(filename);
                    String saveDirs = makeDirs(saveFilename, rootPath);
                    String saveRelativePath = "tools\\"+saveDirs+"\\"+saveFilename;
                    System.out.println("保存的文件名："+saveFilename);
                    System.out.println("保存的文件路径："+saveRelativePath);
                    FileOutputStream out = new FileOutputStream(rootPath+saveRelativePath);
                    byte buffer[] = new byte[1024];
                    int len = 0;
                    while((len=in.read(buffer))!=-1){
                        out.write(buffer, 0, len);
                    }
                    tool.setT_path(rootPath.replace("\\","/")+"/"+saveRelativePath.replace("\\","/"));
                    System.out.println("mysql_save_path = " + tool.getT_path());
                    in.close();
                    out.close();
                    item.delete();
                }
            }
            json.put("success",1);
            json.put("message","文件上传成功！");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            tool.setT_time(sdf.format(new Date()).toString());
            toolService.uploadTool(tool);
        }catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            json.put("success",0);
            json.put("message","单个文件超出最大值！");
        }catch (FileUploadBase.SizeLimitExceededException e) {
            e.printStackTrace();
            json.put("success",0);
            json.put("message","上传文件的总的大小超出限制的最大值！");
        }catch (Exception e) {
            e.printStackTrace();
            json.put("message","服务器错误!");
        }
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());
    }

    private String makeFileName(String filename){  //2.jpg
        return UUID.randomUUID().toString() + "_" + filename;
    }

    private String makeDirs(String filename,String rootPath){
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  //0--15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        String dir = rootPath + "\\tools\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
        File file = new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
        return dir1+"\\"+dir2;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
