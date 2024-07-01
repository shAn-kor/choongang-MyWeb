package com.myweb.board.model;

import java.sql.Timestamp;

public class BoardDTO {
    private int bno;
    private String writer;
    private String title;
    private String content;
    private Timestamp regdate;
    private int hit;

    public BoardDTO() {}
    public BoardDTO(int bno, String writer, String title, String content, Timestamp regdate, int hit) {
        this.bno = bno;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.regdate = regdate;
        this.hit = hit;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
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

    public Timestamp getRegdate() {
        return regdate;
    }

    public void setRegdate(Timestamp regdate) {
        this.regdate = regdate;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }
}
