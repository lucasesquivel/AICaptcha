// import java.io.*;
// import java.net.*;
// import java.net.http.HttpClient;
// import java.net.http.HttpResponse;

// import org.apache.hc.client5.http.classic.methods.HttpPost;
// import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
// import org.apache.hc.core5.http.io.entity.StringEntity;
// import org.json.*;

// public class DALLEAPI {
//   public static void main(String[] args) throws Exception {
//     // Replace <API_KEY> with your actual API key
//     String apiKey = "<API_KEY>";
    
//     // Construct the API request payload with image generation parameters
//     JSONObject payload = new JSONObject();
//     payload.put("model", "image-alpha-001");
//     payload.put("prompt", "a cat sitting on a couch");
//     payload.put("size", "1024x1024");
//     payload.put("response_format", "url");
    
//     // Create an HTTP request with API key and payload as body
//     HttpPost request = new HttpPost("https://api.openai.com/v1/images/generations");
//     request.setHeader("Content-Type", "application/json");
//     request.setHeader("Authorization", "Bearer " + apiKey);
//     StringEntity entity = new StringEntity(payload.toString());
//     request.setEntity(entity);
    
//     // Send the HTTP request and retrieve the JSON response
//     HttpClient httpClient = HttpClientBuilder.create().build();
//     HttpResponse response = httpClient.execute(request);
//     BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//     String line;
//     StringBuilder sb = new StringBuilder();
//     while ((line = rd.readLine()) != null) {
//       sb.append(line);
//     }
//     rd.close();
    
//     // Parse the JSON response to extract image URLs
//     JSONObject json = new JSONObject(sb.toString());
//     String imageUrl = json.getString("data");
    
//     // Download the image using the URL and save it to a file or in-memory data structure
//     URL url = new URL(imageUrl);
//     InputStream is = url.openStream();
//     OutputStream os = new FileOutputStream("dalle_image.jpg");
//     byte[] buffer = new byte[4096];
//     int bytesRead;
//     while ((bytesRead = is.read(buffer)) != -1) {
//       os.write(buffer, 0, bytesRead);
//     }
//     is.close();
//     os.close();
//   }
// }
