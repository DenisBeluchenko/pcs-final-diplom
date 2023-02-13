public class Pdf {
    private String string;
    private String pdfName;
    private int page;
    private int count;

    public int getCount() {
        return count;
    }

    public String getString() {
        return string;
    }

    public Pdf(String string, String pdfName, int page, int count) {
        this.string = string;
        this.pdfName = pdfName;
        this.page = page;
        this.count = count;
    }

    public String getPdfName() {
        return pdfName;
    }

    public int getPage() {
        return page;
    }
}
