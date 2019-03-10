package ru.ex;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsEqual.equalTo;

public class Tests {

    @Test
    public void getCows() throws Exception {
        StringBuffer usWord = new StringBuffer("feabc");
        StringBuffer hideWord = new StringBuffer("abcdef");

        assertThat(Main.getCows(usWord, hideWord), is(equalTo(5)));
    }

    @Test
    public void getBullsNull() throws Exception {
        StringBuffer usWord = new StringBuffer("feabc");
        StringBuffer hideWord = new StringBuffer("abcdef");

        assertThat(Main.getBulls(usWord, hideWord), is(equalTo(0)));
    }

    @Test
    public void getBulls() throws Exception {
        StringBuffer usWord = new StringBuffer("abjjjf");
        StringBuffer hideWord = new StringBuffer("abcdef");

        assertThat(Main.getBulls(usWord, hideWord), is(equalTo(3)));
    }


    @Test
    public void notNull() throws Exception {
        WordProv wp = new WordProv();
        assertThat(wp.getWord(), is(not(equals(null))));
    }


}
