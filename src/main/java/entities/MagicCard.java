package entities;

public class MagicCard extends BaseEntity{
    private String name;
    private String uri;
    private int mana_cost;
    private String type_line;
    private String oracle_text;
    private String colors;
    private MagicSet set;

    public MagicCard(){}

    public MagicCard(int id, String name, String uri, int mana_cost, String type_line, String oracle_text, String colors, MagicSet set) {
        super(id);
        this.name = name;
        this.uri = uri;
        this.mana_cost = mana_cost;
        this.type_line = type_line;
        this.oracle_text = oracle_text;
        this.colors = colors;
        this.set = set;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getMana_cost() {
        return mana_cost;
    }

    public void setMana_cost(int mana_cost) {
        this.mana_cost = mana_cost;
    }

    public String getType_line() {
        return type_line;
    }

    public void setType_line(String type_line) {
        this.type_line = type_line;
    }

    public String getOracle_text() {
        return oracle_text;
    }

    public void setOracle_text(String oracle_text) {
        this.oracle_text = oracle_text;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public MagicSet getSet() {
        return set;
    }

    public void setSet(MagicSet set) {
        this.set = set;
    }

    @Override
    public String toString() {
        return "MagicCard{" +
                "name='" + name + '\'' +
                ", uri='" + uri + '\'' +
                ", mana_cost=" + mana_cost +
                ", type_line='" + type_line + '\'' +
                ", oracle_text='" + oracle_text + '\'' +
                ", colors='" + colors + '\'' +
                ", set=" + set +
                "} " + super.toString();
    }
}
