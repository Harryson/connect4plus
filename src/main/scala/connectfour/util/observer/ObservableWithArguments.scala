package connectfour.util.observer

/**
 * Created by maharr on 19.10.15.
 */
class ObservableWithArguments extends Observable {
  private var subscribers = List[IObserverWithArguments]()

  def addObserver(s: IObserverWithArguments) = subscribers = s :: subscribers

  def removeObserver(s: IObserverWithArguments) = subscribers = subscribers.filter(_ != s)

  def notifyObservers(arg: Any) = for(subscriber <- subscribers) {
    subscriber.update(arg)
  }
}
