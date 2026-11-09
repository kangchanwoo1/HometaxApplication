package connector.test;

import java.util.Date;

public class BoardDTO {
    // board 테이블의 컬럼들과 1:1로 매칭되는 변수들
    private int boardId;
    private String title;
    private String content;
    private String writer;
    private Date regDate;

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

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    // 3. 데이터를 눈으로 확인하기 위한 toString 메서드
    @Override
    public String toString() {
        return "BoardDTO [boardId=" + boardId + ", title=" + title + ", content=" + content 
                + ", writer=" + writer + ", regDate=" + regDate + "]";
    }
}