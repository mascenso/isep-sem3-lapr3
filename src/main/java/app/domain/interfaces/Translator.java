package app.domain.interfaces;

import javax.swing.text.html.parser.Entity;

public interface Translator<E> {

    E translate(E obj);


}
