//инфо здесь: https://ru.stackoverflow.com/questions/1515527/%d0%9d%d0%b0%d0%b9%d1%82%d0%b8-%d1%80%d0%b5%d1%88%d0%b5%d0%bd%d0%b8%d0%b5-%d0%b2-%d1%86%d0%b8%d0%ba%d0%bb%d0%b5-%d1%81-%d0%b4%d0%b0%d1%82%d0%b0%d0%bc%d0%b8

public class Draft_29_From_Stackoverflow {

//    2 ответа
//    Сортировка:
//
//
//            3
//
//
//
//    Для начала следует правильно прочитать и распарсить XML вида для заданного URL, в частности, чтобы вычислить изменения котировок в первый день месяца, начальная дата должна быть на день раньше (28.02.2023):
//
//<ValCurs ID="R01235" DateRange1="28.02.2023" DateRange2="31.03.2023" name="Foreign Currency Market Dynamic">
//    <Record Date="28.02.2023" Id="R01235">
//        <Nominal>1</Nominal>
//        <Value>75,4323</Value>
//    </Record>
//    <Record Date="01.03.2023" Id="R01235">
//        <Nominal>1</Nominal>
//        <Value>74,8932</Value>
//    </Record>
//    <Record Date="02.03.2023" Id="R01235">
//        <Nominal>1</Nominal>
//        <Value>75,2513</Value>
//    </Record>
//<!-- ... -->
//</ValCurs>
//    в коллекцию экземпляров котировок List<CurrencyRate>, где класс CurrencyRate содержит поля даты котировки (значение атрибута Date) и её значения в элементе Value.
//
//    Для чтения XML данных рекомендуется использовать библиотеку Jackson. Также в представленном решении используется проект Lombok.
//    Фрагмент pom.xml файла:
//
//<!-- pom.xml -->
//    <properties>
//        <jackson.version>2.13.4</jackson.version>
//        <lombok.version>1.18.10</lombok.version>
//    </properties>
//
//    <dependencies>
//        <dependency>
//            <groupId>com.fasterxml.jackson.core</groupId>
//            <artifactId>jackson-databind</artifactId>
//    <version>${jackson.version}</version>
//        </dependency>
//        <dependency>
//            <groupId>com.fasterxml.jackson.dataformat</groupId>
//            <artifactId>jackson-dataformat-xml</artifactId>
//    <version>${jackson.version}</version>
//        </dependency>
//        <dependency>
//            <groupId>com.fasterxml.jackson.datatype</groupId>
//            <artifactId>jackson-datatype-jsr310</artifactId>
//    <version>${jackson.version}</version>
//        </dependency>
//        <dependency>
//            <groupId>org.projectlombok</groupId>
//            <artifactId>lombok</artifactId>
//    <version>${lombok.version}</version>
//        </dependency>
//<!-- ... -->
//    </dependencies>
//<!-- ... -->
//    Пример POJO для чтения корневого элемента в исходном XML:
//    @Data
//    @JacksonXmlRootElement(localName = "ValCurs")
//    public class ValCurs {
//        @JacksonXmlProperty(isAttribute = true, localName = "ID")
//        private String id;
//
//        @JacksonXmlProperty(isAttribute = true, localName = "DateRange1")
//        @JsonFormat(pattern = "dd.MM.yyyy")
//        private LocalDate from;
//
//        @JacksonXmlProperty(isAttribute = true, localName = "DateRange2")
//        @JsonFormat(pattern = "dd.MM.yyyy")
//        private LocalDate to;
//
//        @JacksonXmlProperty(isAttribute = true, localName = "name")
//        private String name;
//
//        @JacksonXmlProperty(localName = "Record")
//        @JacksonXmlElementWrapper(useWrapping = false)
//        private List<CurrencyRate> rates;
//    }
//    Пример POJO для котировки, включая десериализатор для чисел с запятой в качестве десятичного разделителя (излишние поля игнорируются).
//    @Data
//    @JsonIgnoreProperties(ignoreUnknown = true)
//    class CurrencyRate {
//        @JacksonXmlProperty(isAttribute = true, localName = "Date")
//        @JsonFormat(pattern = "dd.MM.yyyy")
//        private LocalDate date;
//
//        @JsonProperty("Value")
//        @JsonDeserialize(using = MyDoubleDeserializer.class, as = Double.class)
//        private double value;
//    }
//    Класс-десериализатор, использующий NumberFormat для немецкой локали:
//    public class MyDoubleDeserializer extends JsonDeserializer<Double> {
//        private static final NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);
//
//        @SneakyThrows
//        @Override
//        public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
//            return nf.parse(jsonParser.getText()).doubleValue();
//        }
//    }
//    Тогда метод для чтения котировок в заданном месяце может быть реализован так:
//
//    @SneakyThrows
//    public static ValCurs readRates(YearMonth month) {
//        LocalDate from = month.atDay(1).minusDays(1);
//        LocalDate to = month.atEndOfMonth();
//        DateTimeFormatter ddMMyyyy = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String url = String.format(
//                "https://cbr.ru/scripts/XML_dynamic.asp?date_req1=%s&date_req2=%s&VAL_NM_RQ=R01235",
//                from.format(ddMMyyyy),
//                to.format(ddMMyyyy)
//        );
//        XmlMapper xmlMapper = new XmlMapper();
//        xmlMapper.registerModule(new JavaTimeModule());
//
//        return xmlMapper.readValue(new URL(url), ValCurs.class);
//    }
//    После получения качественных данных, требуемые значения изменений достаточно просто определить:
//
//    public static void findMaxChanges(List<CurrencyRate> rates) {
//        CurrencyRate prev = rates.get(0);
//        CurrencyRate maxPlus = null;
//        CurrencyRate maxMinus = null;
//        double max = 0.0, min = 0.0, sum = 0.0;
//        for (int i = 1, n = rates.size(); i < n; i++) {
//            CurrencyRate curr = rates.get(i);
//            double change = curr.getValue() - prev.getValue();
//            if (change > 0) {
//                if (maxPlus == null || change > max) {
//                    max = change;
//                    maxPlus = curr;
//                }
//            } else if (change < 0) {
//                if (maxMinus == null || change < min) {
//                    min = change;
//                    maxMinus = curr;
//                }
//            }
//            sum += change;
//            prev = curr;
//        }
//        if (maxPlus != null) {
//            System.out.printf("Максимальный прирост: %.4f соответствует дате %s%n", max, maxPlus.getDate());
//        }
//        if (maxMinus != null) {
//            System.out.printf("Максимальное снижение: %.4f соответствует дате %s%n", min, maxMinus.getDate());
//        }
//        System.out.printf("Суммарное изменение за месяц: %.4f%n", sum);
//        System.out.printf("Среднее ежедневное изменение за месяц: %.4f для %d котировок%n", sum / (rates.size() - 1), rates.size() - 1);
//    }
//    Тест:
//
//    // прочитать котировки
//    ValCurs valCurs = readRates(YearMonth.of(2023, 3));//xmlMapper.readValue(XML, ValCurs.class);
//
//// вывести первых пять записей
//valCurs.getRates().stream().limit(5).forEach(System.out::println);
//
//    // найти максимальные изменения
//    findMaxChanges(valCurs.getRates());
//    Результаты:
//
//    CurrencyRate(date=2023-02-28, value=75.4323)
//    CurrencyRate(date=2023-03-01, value=74.8932)
//    CurrencyRate(date=2023-03-02, value=75.2513)
//    CurrencyRate(date=2023-03-03, value=75.4729)
//    CurrencyRate(date=2023-03-04, value=75.4592)
//    Максимальный прирост: 0.6638 соответствует дате 2023-03-17
//    Максимальное снижение: -0.6489 соответствует дате 2023-03-24
//    Суммарное изменение за месяц: 1.6540
//    Среднее ежедневное изменение за месяц: 0.0752 для 22 котировок
//            Поделиться
//    Править
//            Отслеживать
//    Тревога
//    изменён вчера
//    ответ дан 2 дня назад
//
//    Nowhere Man
//11k1616 золотых знаков1414 серебряных знаков2727 бронзовых знаков
//    Большое спасибо за помощь. – 
//    Vladizzzlav
//    вчера  
//
//    @Vladizzzlav, если ответ оказался полезным, можете принять его, поставив зелёную галочку слева от ответа. – 
//    Nowhere Man
//    вчера
//    Добавить комментарий
//
//2
//
//
//
//    Идея в том, что курс никак не привязан к дате. Он просто сохраняется в списке listCourses без даты, ну и дальше его вытащить никак не возможно, потому что область видимости переменной dateStr находится в пределах первого цикла.
//
//    Вот что можно почитать про область видимости переменных: Объясните про область видимости и использование переменных
//
//    Видимость переменных/классов определяется модификаторами доступа (private, public etc), а также областями видимости внутри блоков кода, ограниченных фигурными скобками {}
//
//    В случае с последними переменная объявленная в к-л блоке кода, окружённая {} видна только внутри этого блока (за исключением случаев, когда это переменная класса, имеющая public или default(т.е. без модификаотора; видна в одном пакете) уровень доступа) и всех внутренних блоках.
//
//    Попробуйте привязать дату к курсу, с помощью Map или создать объект из двух полей, дата и курс и добавлять его в список. Во втором цикле можно вытащить дату вместе с курсом из списка или Map. Вот такая идея, думаю с помощью этого можно легко написать код. Поскольку это учебное задание то придется писать самому.

}
