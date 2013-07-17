package utils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author wujinliang
 * @since 4/15/13
 */
public class EasyList<E> extends ArrayList<E> {
    private static final long serialVersionUID = 3623753774034207089L;

    public EasyList() {
    }

    public EasyList(E e) {
        super.add(e);
    }

    public EasyList<E> easyAdd(E e) {
        super.add(e);
        return this;
    }

    public EasyList<E> easyAddAll(Collection<E> es) {
        super.addAll(es);
        return this;
    }

    public EasyList<E> easyRemove(E e) {
        super.remove(e);
        return this;
    }

    public EasyList<E> easyRemove(int pos) {
        super.remove(pos);
        return this;
    }
}
