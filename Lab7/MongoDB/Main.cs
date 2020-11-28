using System;
using System.IO;
using System.Security.Cryptography;
using System.Text;
using MongoDB.Bson;
using MongoDB.Driver;

using System.Collections.Generic;
using System.Linq;

namespace MongoDB
{
    class Program
    {
        public static string randomWord = "";
        static void Main(string[] args)
        {
            MongoClient dbClient = new MongoClient("mongodb://garciuj:Password1@cluster0-shard-00-00.184so.mongodb.net:27017,cluster0-shard-00-01.184so.mongodb.net:27017,cluster0-shard-00-02.184so.mongodb.net:27017/MongoDB?ssl=true&replicaSet=atlas-opxi80-shard-0&authSource=admin&retryWrites=true&w=majority");
            var database = dbClient.GetDatabase("MongoDB");

            var collection = database.GetCollection<BsonDocument>("MongoDB");

            var document = new BsonDocument();


            BsonDocument firstStudent =
        new BsonDocument { { "University", "UTM" }, { "fullName", "Tatiana Tiguliova" }, { "email", " tanyatsyguliova@gmail.com" }, { "dateBirth", "6/08/2000" }, { "sudentGroup", "FAF-182" } }
             ;


            BsonDocument secondStudent =
        new BsonDocument { { "University", "UTM" }, { "fullName", "Margareta Galaju" }, { "email", " margogalaju@gmail.com" }, { "dateBirth", "3/06/2001" }, { "sudentGroup", "FAF-181" } }
             ;


            BsonDocument thirdStudent =
        new BsonDocument { { "University", "UTM" }, { "fullName", "Garciu Eugenia" }, { "email", " jeniagarciu@gmail.com" }, { "dateBirth", "18/11/1999" }, { "sudentGroup", "FAF-182" } }
             ;


            database.DropCollection("students");

            collection.InsertMany(new List<BsonDocument> { firstStudent, secondStudent, thirdStudent });

            Console.WriteLine(document.ToString());
            var filter = Builders<BsonDocument>.Filter.Eq("University", "UTM");
            var result = collection.Find(filter).ToList();

            MongoDBController.updateDBWithEncryptedValue("dateBirth", result, collection);

            //MongoDBController.updateDBWithDecryptedValue("dateBirth", result, collection);

        }
    }

}





