import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.lang.String.format;

class ClauseCoverageProcessor implements AnnotationProcessor {

    private Map<String, List<MethodInstance>> predicateMethodInstance = new HashMap<>();

    @Override
    public void process(MethodInstance methodInstance) {
        ClauseCoverage clauseCoverage = (ClauseCoverage) methodInstance.getAnnotation();

        List<MethodInstance> methodInstances = predicateMethodInstance.get(clauseCoverage.predicate());
        if (methodInstances == null)
            methodInstances = new ArrayList<>();
        methodInstances.add(methodInstance);
        predicateMethodInstance.put(clauseCoverage.predicate(), methodInstances);
    }

    public boolean canProcess(Annotation annotation) {
        return annotation instanceof ClauseCoverage;
    }

    @Override
    public void commit() {

    }

    public void commit(Document document) {

        for (Map.Entry<String, List<MethodInstance>> entry : (predicateMethodInstance.entrySet())){
            Element cc = document.getElementById("CC");
            cc.append(format("<h1> Predicate: %s </h1>", entry.getKey()));
            List<MethodInstance> valuations = entry.getValue();
            cc.append("<table> </table>");
            for (MethodInstance methodInstance : valuations){
                ClauseCoverage clauseCoverage = (ClauseCoverage) methodInstance.getAnnotation();
                Stream<Valuation> sorted = Stream.of(clauseCoverage.valuations()).sorted();
            }
        }

    }


}
