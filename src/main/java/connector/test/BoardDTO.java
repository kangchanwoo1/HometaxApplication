package connector.test;

import java.util.Date;
import java.sql.Timestamp;

public class BoardDTO {
    // board 테이블의 컬럼들과 1:1로 매칭되는 변수들
    private int boardId;
    private String title;
    private String content;
    private String writer;
    private Timestamp reg_date;

    // 1. 기본 생성자
    public BoardDTO() {}

    // 2. Getter와 Setter
    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }


    public void setRegDate(Timestamp reg_date) {
        this.reg_date = reg_date;
    }
    
    public Timestamp getRegDate() {
        return reg_date;
    }

    }
