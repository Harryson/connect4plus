package connectfour.util.observer

/**
 * Created by maharr on 19.12.15.
 */
class ObservableWithArguments extends Observable {

  def notifyObservers(arg: Any) {
    notifyObservers(subscribers, arg)
  }

  private def notifyObservers(subscribersList: List[_ <: IObserver], arg: Any) {
    if (subscribersList.nonEmpty) {
      subscribersList.head.asInstanceOf[IObserverWithArguments].update(arg)
      notifyObservers(subscribersList.tail, arg)
    }
  }
}