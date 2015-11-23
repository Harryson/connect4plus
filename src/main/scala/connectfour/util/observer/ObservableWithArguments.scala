package connectfour.util.observer

/**
 * Created by maharr on 19.10.15.
 */
class ObservableWithArguments extends Observable {
  def notifyObservers(arg: Any) = for(subscriber <- subscribers) {
    subscriber.asInstanceOf[IObserverWithArguments].update(arg)
  }
}
