package mine.logutils;

import androidx.annotation.NonNull;

import com.apkfuns.logutils.Parser;

/**
 * Created by Administrator on 2021/2/27.
 */
public class PersonParser implements Parser<Person> {

    @NonNull
    @Override
    public Class<Person> parseClassType() {
        System.out.println("~~" + getClass().getSimpleName() + ".parseClassType~~");
        return Person.class;
    }

    @Override
    public String parseString(@NonNull Person person) {
        System.out.println("~~" + getClass().getSimpleName() + ".parseString~~");
        System.out.println("person = " + person);
        return person.toString();
    }
}
