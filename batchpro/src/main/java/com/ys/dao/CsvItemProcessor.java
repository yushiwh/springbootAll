package com.ys.dao;

import com.ys.bean.Person;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

/**
 * 数据处理及校验
 * Created by yushi on 2017/3/2.
 */
public class CsvItemProcessor extends ValidatingItemProcessor<Person> {

    @Override
    public Person process(Person item) throws ValidationException {
        super.process(item);//需要执行 super.process(item)才会调用自定义校验器

        if (item.getNation().equals("汉族")) {//对数据进行简单的处理
            item.setNation("01");
        } else {
            item.setNation("02");
        }
        return item;
    }
}
