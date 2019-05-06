# Report Simplifier

Report Simplifier is a small library that you can use to work with JasperReports® generation without using a JasperReports® Server.

## Getting Started

First you need to add the lib to your project, then create a DTO, extending GenericDTO:

```java
public class ExampleDTO extends GenericDTO {

    ExampleDTO(JRDataSource dataSource){
        super(dataSource);
    }
    
    //Fill the DTO with all the parameters that your report will use
    
    private String name;
    
    //Getters and Setters
    
    @Override
    public Map<String, Object> getParameters() {
    
        //Fill the getParameters method with your object parameters representation
        
        Map<String, Object> map = new HashMap<>();
        map.put("PRM_NAME", this.getName());
        return map;
    }

}
```

After this you can instantiate a new GenericReportService, to get the report File with the getReport method:

```java
    //Example to get PDF report:
    GenericReportService service = new GenericReportService("<your_jasper_file_path>");
    try {
        File file = service.getReport(ReportType.PDF, dto, "<your_desired_save_location>");
    } catch (ReportSimplifierException e) {
        e.printStackTrace();
    }
```

This will generate your file.

###New DTO implementation added:

Now you can transform any model in IReportDTO with Java annotations.

Example:

```java
@RSModel //Annotation that represents a ReportSimplifier Model, which can be turned in IReportDTO Object
public class ExampleModel {
    
    @RSParameter(name="PARAMETER") //Annotation that represents a IReportDTO parameter.
    private String parameter;
    
    @RSDataSource //Annotation that represents a IReportDTO dataSource.
    private JRDataSource dataSource;
    
    //Getters and Setters

}
```
This code can be turned into IReportDTO Object creating a new instance of AnnotationGeneratedDTO, passing to constructor an instance of your model.

Example:

```java
ExampleModel model = new ExampleModel();

model.setParameter("FIRST PARAMETER");
model.setDataSource(new JREmptyDataSource());

IReportOutputDTO dto = new AnnotationGeneratedDTO(model);
```

RSParameter can receive a specific getter adding parameter "getter" to annotation.

## Author

* **Paulo Mendes** - [Visit my WebSite](https://paulomendes.dev)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
