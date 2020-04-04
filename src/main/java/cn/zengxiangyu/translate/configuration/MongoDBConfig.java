package cn.zengxiangyu.translate.configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDBConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongodbURL;

    @Bean
    public MongoDatabase createMongoDatabase() {
        MongoClientURI mongoClientURI = new MongoClientURI(mongodbURL);
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        return mongoDatabase;
    }
}
