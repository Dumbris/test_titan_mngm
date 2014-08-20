package ac.wayv.app

import com.thinkaurelius.titan.core.{TitanLabel, TitanKey, TitanFactory}
import org.apache.commons.configuration.BaseConfiguration

import scala.collection.JavaConversions._

import com.thinkaurelius.titan.core.{PropertyKey, TitanFactory}
import com.thinkaurelius.titan.core.schema.{ConsistencyModifier, TitanGraphIndex}
import com.tinkerpop.blueprints.Vertex
import org.apache.commons.configuration.BaseConfiguration
import com.thinkaurelius.titan.graphdb.blueprints.TitanBlueprintsGraph
import com.tinkerpop.blueprints.Direction

object ConsumerApp extends App {
  val g = TitanFactory.open("/home/algis/workspace/test_titan_mngm_2/conf/titan-cassandra-embedded-es.properties")

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
    val key_uid = mgmt.makePropertyKey("uid").dataType(classOf[String]).make()
    val uid_index : TitanGraphIndex = mgmt.buildIndex("uid", classOf[Vertex]).addKey(key_uid).unique().buildCompositeIndex()
    mgmt.setConsistency(uid_index, ConsistencyModifier.LOCK)
    val key_title = mgmt.makePropertyKey("title").dataType(classOf[String]).make()
    val key_description = mgmt.makePropertyKey("description").dataType(classOf[String]).make()
    mgmt.buildIndex("texts", classOf[Vertex]).addKey(key_title).addKey(key_description).buildMixedIndex(INDEX_NAME)
    mgmt.commit()
  }

  val juno = g.addVertex(null)
  juno.setProperty("name", "juno")
  val jupiter = g.addVertex(null)
  jupiter.setProperty("name", "jupiter")
  val merried = g.addEdge(null, juno, jupiter, "married")
  //Returns all defined keys in the graph
  for (x <- g.getTypes(classOf[TitanKey]))
    println(x)
  //Returns all defined labels in the graph
  for (x <- g.getTypes(classOf[TitanLabel]))
    println(x)
  for (x <- juno.query.labels("married").vertices)
    println(x.getProperty("name"))
}
