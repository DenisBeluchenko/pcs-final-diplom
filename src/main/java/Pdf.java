import java.util.Map;

public class Pdf {
    private String pdfName;
    private int page;
    private Map<String, Integer> pdf;

    public Map<String, Integer> getPdf() {
        return pdf;
    }

    public Pdf(String pdfName, int page, Map<String, Integer> pdf) {
        this.pdfName = pdfName;
        this.page = page;
        this.pdf = pdf;
    }

    public String getPdfName() {
        return pdfName;
    }

    public int getPage() {
        return page;
    }
}
