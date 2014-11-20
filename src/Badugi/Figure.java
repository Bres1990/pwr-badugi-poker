package Badugi;

/**
 * Created by Adam on 2014-11-20.
 */


public enum Figure
{
    ace(13), f2(12), f3(11), f4(10), f5(9), f6(8), f7(7), f8(6), f9(5), f10(4),
    jack(3), queen(2), king(1);

    private final int value;
    private Figure(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}
