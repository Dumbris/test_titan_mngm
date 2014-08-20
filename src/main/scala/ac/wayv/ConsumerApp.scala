package ac.wayv.app

import com.thinkaurelius.titan.core.{TitanFactory}
import org.apache.commons.configuration.BaseConfiguration

import scala.collection.JavaConversions._

import com.thinkaurelius.titan.core.{PropertyKey, TitanFactory}
import com.thinkaurelius.titan.core.schema.{SchemaAction, ConsistencyModifier, TitanGraphIndex}
import com.tinkerpop.blueprints.Vertex
import org.apache.commons.configuration.BaseConfiguration
import com.thinkaurelius.titan.graphdb.blueprints.TitanBlueprintsGraph
import com.tinkerpop.blueprints.Direction

object ConsumerApp extends App {
  val g = TitanFactory.open("/home/algis/workspace/test_titan_mngm_2/conf/titan-cassandra-embedded-es.properties")
  def uuid = java.util.UUID.randomUUID.toString
  createIndexes()

  def createIndexes() : Unit = {
    /* Make ES indexes */
    val INDEX_NAME: String = "search"
    val mgmt = g.getManagementSystem()
    /*
    val name : PropertyKey = mgmt.makePropertyKey("name").dataType(classOf[String]).make()
    val namei : TitanGraphIndex = mgmt.buildIndex("name", classOf[Vertex]).addKey(name).unique().buildCompositeIndex()
    mgmt.setConsistency(namei, ConsistencyModifier.LOCK)
    */
    //println(mgmt.getPropertyKey("uid"))
    mgmt.getGraphIndexes(classOf[Vertex]).map(_.getName) foreach println
    mgmt.updateIndex("uid", SchemaAction.REMOVE_INDEX)
    /*
    val key_uid = mgmt.makePropertyKey("uid2").dataType(classOf[String]).make()
    val uid_index : TitanGraphIndex = mgmt.buildIndex("uid2", classOf[Vertex]).addKey(key_uid).unique().buildCompositeIndex()
    mgmt.setConsistency(uid_index, ConsistencyModifier.LOCK)
    val key_title = mgmt.makePropertyKey("title").dataType(classOf[String]).make()
    val key_description = mgmt.makePropertyKey("description").dataType(classOf[String]).make()
    mgmt.buildIndex("texts", classOf[Vertex]).addKey(key_title).addKey(key_description).buildMixedIndex(INDEX_NAME)
    mgmt.commit()*/
  }

  val juno = g.addVertex(null)
  juno.setProperty("uid", uuid)
  juno.setProperty("title", "test12")
  juno.setProperty("description", uuid)
  val jupiter = g.addVertex(null)
  jupiter.setProperty("uid", uuid)
  jupiter.setProperty("title", "test24")
  jupiter.setProperty("description", uuid)
  val merried = g.addEdge(null, juno, jupiter, "married")
  g.commit()
  g.indexQuery("texts", s"v.title:(test*)").vertices().map(_.getElement).foreach((v : Vertex) => {
    println("-------------------")
    println(v.getProperty("uid"))
    println(v.getProperty("title"))
    println(v.getProperty("description"))
  })
}
