package marlon.com.libiki;

/**
 * Created by marlon on 04/12/17.
 */

public class Commande {
    private String article;
    private long prix;
    private long id_client;

    public long getId_client() {
        return id_client;
    }

    public void setId_client(long id_client) {
        this.id_client = id_client;
    }

    public Commande(String article, long prix, long id_client) {
        setArticle(article);
        setPrix(prix);
        setId_client(id_client);
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        if(!article.isEmpty()){
        this.article = article;}
    }

    public long getPrix() {
        return prix;
    }

    public void setPrix(long prix) {
        if(prix>0){
        this.prix = prix;}
    }
}
