package connectfour

import com.google.inject.AbstractModule

/**
 * Created by maharr on 19.10.15.
 */
class GameControllerModule extends AbstractModule{
  @Override protected def configure {
    // TODO
    
    /*bind(classOf[IController]).to(classOf[GameController])
    bind(classOf[IObserverWithArguments]).to(classOf[GameController])
    bind(classOf[ISaveGameDAO]).annotatedWith(Names.named("hibernate")).to(classOf[SaveGameDbHibernate])
    bind(classOf[ISaveGameDAO]).annotatedWith(Names.named("db4o")).to(classOf[SaveGameDb4oDAO])
    bind(classOf[ISaveGameDAO]).annotatedWith(Names.named("couchdb")).to(classOf[SaveGameCouchDbDAO])
    //bind(ISaveGameDAO.class).to(SaveGameDbHibernate.class);
    //bind(ISaveGameDAO.class).to(SaveGameCouchDbDAO.class);
    bind(classOf[ISaveGameDAO]).to(classOf[SaveGameDb4oDAO])
    bind(classOf[IHighScoreController]).to(classOf[HighScoreController])
    bind(classOf[SolverPlugin]).to(classOf[MiddleSolver])*/
  }
}
