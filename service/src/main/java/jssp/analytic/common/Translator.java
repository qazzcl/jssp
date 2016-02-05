package jssp.analytic.common;

public interface Translator<Source, Target> {
    Target translate(Source source);
}
