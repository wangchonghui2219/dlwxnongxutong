package com.example.administrator.dlwxnongxutong.bean;


public class VersionBean {
    private final int code;

    private final String message;

    private final Info info;

    public VersionBean(int code, String message, Info info) {
        this.code = code;
        this.message = message;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Info getInfo() {
        return info;
    }

    public static class Info {
        private final int editionNo;

        private final String editioninfo;

        private final String editionName;

        private final String updatetime;

        private final String download;

        public Info(int editionNo, String editioninfo, String editionName, String updatetime,
                    String download) {
            this.editionNo = editionNo;
            this.editioninfo = editioninfo;
            this.editionName = editionName;
            this.updatetime = updatetime;
            this.download = download;
        }

        public int getEditionNo() {
            return editionNo;
        }

        public String getEditioninfo() {
            return editioninfo;
        }

        public String getEditionName() {
            return editionName;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public String getDownload() {
            return download;
        }
    }
}
