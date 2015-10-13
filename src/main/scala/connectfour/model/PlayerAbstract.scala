package connectfour.model

import connectfour.util.observer.ObservableWithArguments;

abstract class PlayerAbstract(var name: String) extends ObservableWithArguments with Player {
  private var gameField: GameField = _

  override def getGameField() = {
    gameField
  }

  override def setGameField(gameField: GameField) {
    try {
      if (gameField != null) {
        this.gameField = gameField.clone()
      } else { // db4o needs to set the players gameField to null, in order not to save it. 
        this.gameField = null
      }
    } catch {
      case e: Exception =>
    }
  }
  
    override def equals(o: Any): Boolean = {
        if (this eq o.asInstanceOf[AnyRef]) {
          return true
        }
        
        if (o == null || getClass() != o.getClass()) {
          return false
        }

        val that = o.asInstanceOf[PlayerAbstract];

        return !(if (name != null) !name.equals(that.name) else that.name != null);

    }

    override def hashCode(): Int = {
        if (name != null) name.hashCode() else 0
    }

}