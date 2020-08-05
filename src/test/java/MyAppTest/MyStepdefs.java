package MyAppTest;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

//import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class MyStepdefs {



  @Given("^prepare text")
  public void prepare_text() {

    String something = "proba";
    String s = "s";

    String oldstring = "2011-09-09 03:06:00";
    LocalDateTime datetime = LocalDateTime.parse(oldstring, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    int intMonth = datetime.getMonthValue();
    String month = (intMonth < 10) ? "0"+intMonth : String.valueOf(intMonth);
    int intDay = datetime.getDayOfMonth();
    String day = (intDay < 10) ? "0"+intDay : String.valueOf(intDay);
    String minute = (datetime.getMinute()<10) ? "0"+datetime.getMinute() : String.valueOf(datetime.getMinute());
    String hour = (datetime.getHour()<12) ? datetime.getHour()+":"+minute+" AM" : datetime.getHour()-12+
            ":"+minute+" PM";
    String newDate = month+"/"+day+"/"+datetime.getYear()+" "+hour;
    System.out.println(newDate);


  }

  @When("^write text$")
  public void write_text() throws Throwable {
    String user = "xx aa bb";
      String formattedUser = user.toLowerCase().replaceAll("\\s", ".");
    System.out.println(ConfigLoader.read(formattedUser));
    }




}
