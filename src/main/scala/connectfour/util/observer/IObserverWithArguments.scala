package connectfour.util.observer

/**
 * Created by maharr on 19.10.15.
 */
trait IObserverWithArguments extends IObserver {
  def update(arg: Any)
  def update = {}
}
