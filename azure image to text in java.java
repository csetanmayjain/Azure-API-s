//image to text by azure from local image
import java.io.File;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class Main {
    // **********************************************
    // *** Update or verify the following values. ***
    // **********************************************

    // Replace <Subscription Key> with your valid subscription key.
    private static final String subscriptionKey = "";

    // You must use the same Azure region in your REST API method as you used to
    // get your subscription keys. For example, if you got your subscription keys
    // from the West US region, replace "westcentralus" in the URL
    // below with "westus".
    //
    // Free trial subscription keys are generated in the West Central US region.
    // If you use a free trial subscription key, you shouldn't need to change
    // this region.
    private static final String uriBase =
            "https://westcentralus.api.cognitive.microsoft.com/vision/v2.0/ocr";

    public static void main(String[] args) {
    	String text;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            URIBuilder uriBuilder = new URIBuilder(uriBase);

            uriBuilder.setParameter("language", "unk");
            uriBuilder.setParameter("detectOrientation", "true");

            // Request parameters.
            URI uri = uriBuilder.build();
            HttpPost request = new HttpPost(uri);
            
            
            
         // Request headers.
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
            
            
            // Request body.
            File file = new File("E:\\Desktop\\MiniProject\\Rs\\500note.jpg");
            FileEntity reqEntity = new FileEntity(file);
            request.setEntity(reqEntity);
            
            // Call the REST API method and get the response entity.
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // Format and display the JSON response.
                String jsonString = EntityUtils.toString(entity);
                JSONObject json = new JSONObject(jsonString);
               System.out.println("REST Response:\n");
               System.out.println(json.toString(2));
               String word=json.toString();
               System.out.println(text);
                
         }
        } catch (Exception e) {
            // Display error message.
            System.out.println(e.getMessage());
        }
        
    }
    
   
}