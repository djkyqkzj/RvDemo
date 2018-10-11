package com.example.tony.rvdemo;

import java.io.Serializable;
import java.util.List;

/**
 * @author qdafengzi
 * @date 2018/10/9 3:50 PM
 */
public class Bean implements Serializable {
    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private List<DatasBean> datas;

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean implements Serializable {
        private String fixPlantNum;
        private List<JarInfoBean> jarInfo;

        public String getFixPlantNum() {
            return fixPlantNum;
        }

        public void setFixPlantNum(String fixPlantNum) {
            this.fixPlantNum = fixPlantNum;
        }

        public List<JarInfoBean> getJarInfo() {
            return jarInfo;
        }

        public void setJarInfo(List<JarInfoBean> jarInfo) {
            this.jarInfo = jarInfo;
        }

        public static class JarInfoBean implements Serializable {

            private String jarNum;
            private boolean isSelected;


            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }

            public String getJarNum() {
                return jarNum;
            }

            public void setJarNum(String jarNum) {
                this.jarNum = jarNum;
            }

            @Override
            public String toString() {
                return "JarInfoBean{" +
                        "jarNum='" + jarNum + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DatasBean{" +
                    "fixPlantNum='" + fixPlantNum + '\'' +
                    ", jarInfo=" + jarInfo +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Bean{" +
                "message='" + message + '\'' +
                ", datas=" + datas +
                '}';
    }
}
