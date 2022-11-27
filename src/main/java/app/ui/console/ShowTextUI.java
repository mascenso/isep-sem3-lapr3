package app.ui.console;

import org.junit.platform.commons.util.StringUtils;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class ShowTextUI implements Runnable{

    private String text;
    public ShowTextUI(String text)
    {
        if (StringUtils.isBlank(text))
            throw new IllegalArgumentException("ShowTextUI does not support null or empty text");

        this.text = text;
    }
    public void run()
    {
        System.out.println("\n");
        System.out.println(this.text);
        System.out.println("\n");
    }
}
