package connectfour.util.observer

/**
 * Created by maharr on 19.12.15.
 */
class Observable {
  var subscribers: List[_ <: IObserver] = Nil

  def getSubscribers = subscribers
  
  def addObserver[B <: IObserver](s: B) = subscribers = s :: subscribers
  
  def addAllObservers[B <: IObserver](list: List[B]) = subscribers = list ::: subscribers

  def removeObserver[B <: IObserver](s: B) = subscribers = subscribers.filter(_ != s)

  def removeAllObservers = subscribers = Nil

  def notifyObservers() = for(subscriber <- subscribers) {
    subscriber.update
  }
}