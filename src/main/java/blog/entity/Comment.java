package blog.entity;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
public class Comment {
    private Integer com_id;
    private Integer art_id;
    private Integer u_id;
    private String com_content;
    private String com_time;
    private Integer com_agree;
    private Integer com_disagree;
    private String u_img;
    private String u_name;

    public String getU_img() {
        return u_img;
    }

    public void setU_img(String u_img) {
        this.u_img = u_img;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public Integer getCom_id() {
        return com_id;
    }

    public void setCom_id(Integer com_id) {
        this.com_id = com_id;
    }

    public Integer getArt_id() {
        return art_id;
    }

    public void setArt_id(Integer art_id) {
        this.art_id = art_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getCom_content() {
        return com_content;
    }

    public void setCom_content(String com_content) {
        this.com_content = com_content;
    }

    public String getCom_time() {
        return com_time;
    }

    public void setCom_time(String com_time) {
        this.com_time = com_time;
    }

    public Integer getCom_agree() {
        return com_agree;
    }

    public void setCom_agree(Integer com_agree) {
        this.com_agree = com_agree;
    }

    public Integer getCom_disagree() {
        return com_disagree;
    }

    public void setCom_disagree(Integer com_disagree) {
        this.com_disagree = com_disagree;
    }
}
