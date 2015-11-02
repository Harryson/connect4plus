package observer

import scala.collection.immutable.List

/**
 * Created by stefano on 19.02.14.
 */

class Observable {
  private var observer: List[Observer] = Nil

  def addObserver(o: Observer) =
    observer = o :: observer

  def removeObserver(o: Observer) =
    observer = observer.filter(element => element != o)

  def removeAllObservers() = observer = Nil

  def notifyObservers() = {
    for (observer <- observer)
      observer.update
  }
}