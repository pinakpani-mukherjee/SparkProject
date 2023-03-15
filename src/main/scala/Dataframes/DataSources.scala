package Dataframes

import org.apache.spark.sql.types._
import org.apache.spark.sql.{SaveMode, SparkSession}

object DataSources extends App {
  val spark = SparkSession.builder()
    .appName("Data Sources and Formats")
    .config("spark.master", "local")
    .getOrCreate()
  val carsSchema = StructType(Array(
    StructField("Name", StringType),
    StructField("Miles_per_Gallon", DoubleType),
    StructField("Cylinders", LongType),
    StructField("Displacement", DoubleType),
    StructField("Horsepower", LongType),
    StructField("Weight_in_lbs", LongType),
    StructField("Acceleration", DoubleType),
    StructField("Year", StringType),
    StructField("Origin", StringType)
  ))
  /*
  Reading a DF:
  -format
  -schema
   */
  val carsDF = spark.read
    .format("json")
    .schema(carsSchema)
    .option("mode", "failFast") // other options dropMalformed,permissive(default)
    .option("path", "src/main/resources/data/cars.json")
    .load()

  val carsDFWithOptionMap = spark.read
    .format("json")
    .schema(carsSchema)
    .options(Map(
      "mode" -> "failFast",
      "path" -> "src/main/resources/data/cars.json",
    ))
    .load()

  //carsDFWithOptionMap.show()
  /*Write a DF
  -format
  -save mode = overwrite,append,ignore,errorIfExists
  -path
  -kwargs
   */
  carsDF.write
    .format("json")
    .mode(SaveMode.Overwrite)
    .option("path", "src/main/resources/data/cars_duplicate.json")
    .save()
}
