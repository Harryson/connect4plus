package connectfour.util.observer

import controller.GameController

/**
 * Created by maharr on 19.10.15.
 */
class Observable {
  private var subscribers = List[IObserver]()

  def addObserver(s: IObserver) = subscribers = s :: subscribers

  def removeObserver(s: IObserver) = subscribers = subscribers.filter(_ != s)

  def removeAllObservers = subscribers = List[IObserver]()

  def notifyObservers() = for(subscriber <- subscribers) {
    subscriber.update
  }
}