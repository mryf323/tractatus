# Introduction
Tractatus is a library for logic test documentation. [Tractatus](https://en.wikipedia.org/wiki/Tractatus_Logico-Philosophicus) is one of the most complex philosophical books ever written.
# Documentation
If you want to use this library you just need to know these annotations.
* [@ClauseCoverage](https://mryf323.github.io/tractatus/com/github/mryf323/tractatus/ClauseCoverage.html)
* [@UniqueTruePoint](https://mryf323.github.io/tractatus/com/github/mryf323/tractatus/UniqueTruePoint.html)
* [@NearFalsePoint](https://mryf323.github.io/tractatus/com/github/mryf323/tractatus/NearFalsePoint.html)
* [@CACC (Correlated Active Clause Coverage)](https://mryf323.github.io/tractatus/com/github/mryf323/tractatus/CACC.html)
* [@Valuation](https://mryf323.github.io/tractatus/com/github/mryf323/tractatus/Valuation.html)   
* [@ClauseDefinition](https://mryf323.github.io/tractatus/com/github/mryf323/tractatus/ClauseDefinition.html)

## Reporting
Simply add the extension to your test classes and see the reports in the `tractatus` directory in your project root.
Currently, reporting Junit 5 extension is experimental so feel free to report any issues. 
```
@ExtendWith(ReportingExtension.class)
class YourTestClass  {
```


If you want to contribute to this project you can study all of its javadoc [here](https://mryf323.github.io/tractatus).
# Dependency
## Maven
```
<dependency>
  <groupId>com.github.mryf323</groupId>
  <artifactId>tractatus</artifactId>
  <version>1.0.1</version>
</dependency>
```

## Gradle
```
implementation 'com.github.mryf323:tractatus:1.0.0'
```
