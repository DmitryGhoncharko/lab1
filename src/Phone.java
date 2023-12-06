import java.util.Objects;

public class Phone {
    private final String model;
    private final double price;
    private final int weight;

    public Phone(String model, double price, int weight) {
        this.model = model;
        this.price = price;
        this.weight = weight;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Double.compare(price, phone.price) == 0 && weight == phone.weight && Objects.equals(model, phone.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, price, weight);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "model='" + model + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }
}
