import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BooleanSearchEngine implements SearchEngine {
    private List<Pdf> pdfList = new ArrayList<>();

    public BooleanSearchEngine(File pdfsDir) throws IOException {
        for (File file : pdfsDir.listFiles()) {
            var doc = new PdfDocument(new PdfReader(file));
            for (int pages = 1; pages <= doc.getNumberOfPages(); pages++) {
                PdfPage page = doc.getPage(pages);
                String text = PdfTextExtractor.getTextFromPage(page);
                String[] words = text.split("\\P{IsAlphabetic}+");
                Map<String, Integer> freqs = new HashMap<>();
                for (var word : words) {
                    if (word.isEmpty()) {
                        continue;
                    }
                    word = word.toLowerCase();
                    freqs.put(word, freqs.getOrDefault(word, 0) + 1);
                }
                for (String key : freqs.keySet()) {
                    pdfList.add(new Pdf(key, file.getName(), pages, freqs.get(key)));
                }
            }
        }
    }

    @Override
    public List<PageEntry> search(String word) {
        List<PageEntry> pageEntryList = new ArrayList<>();
        pdfList.stream().filter(x -> x.getString().equals(word)).forEach(x -> pageEntryList.add(new PageEntry(x.getPdfName(), x.getPage(), x.getCount())));
        return pageEntryList.stream().sorted(PageEntry::compareTo).collect(Collectors.toList());
    }
}
