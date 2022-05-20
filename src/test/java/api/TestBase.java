package api;

public class TestBase {
    /**
     *  Get data provider
     *
     * @param  data required test parameters data map
     * @return      data provider type
     */
    protected Object[][] getDataProvider(Object[] data) {

        Object[][] dataProvider = new Object[data.length][];

        for (int i = 0; i < data.length; i++) {
            dataProvider[i] = new Object[]{data[i]};
        }

        return dataProvider;
    }
}
