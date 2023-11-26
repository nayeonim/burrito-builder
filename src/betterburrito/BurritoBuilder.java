package betterburrito;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import burrito.PortionSize;
import burrito.Protein;
import burrito.Size;
import burrito.Topping;

/**
 * This class represents a burrito builder for a burrito type T.
 * Every product of this builder has a size, toppings, and proteins.
 *
 * @param <T> the type of burrito to be built
 */
public abstract class BurritoBuilder<T> {
  protected Size size;
  protected Map<Topping, PortionSize> toppings;
  protected Map<Protein,PortionSize> proteins;

  /**
   * Constructs a burrito builder with a null size,
   * and a new hashmap for the toppings and protein.
   */
  public BurritoBuilder() {
    this.toppings = new HashMap<>();
    this.proteins = new HashMap<>();
  }

  /**
   * Returns a burrito builder with the size set as the given Size value.
   *
   * @param s the given Size for the burrito builder to be set to
   * @return a burrito builder with the updated size
   */
  public T size(Size s) {
    this.size = Objects.requireNonNull(s);
    return self();
  }

  /**
   * Returns a burrito builder with an added topping of the given topping type and portion size.
   *
   * @param t the type of topping to be added
   * @param s the portion size that the topping is set to
   * @return a burrito builder with the updated topping
   */
  public T addTopping(Topping t, PortionSize s) {
    this.toppings.put(t, s);
    return self();
  }

  /**
   * Builds an ObservableBurrito with the size, toppings, and proteins of this builder.
   *
   * @return an ObservableBurrito with the size, toppings, and proteins of this builder
   * @throws IllegalStateException if the size is not set
   */
  public abstract ObservableBurrito build() throws IllegalStateException;

  /**
   * Returns itself.
   * @return itself
   */
  protected abstract T self();

  /**
   * Returns burrito builder itself.
   * @return the burrito builder itself
   */
  public abstract T returnBuilder();
}
