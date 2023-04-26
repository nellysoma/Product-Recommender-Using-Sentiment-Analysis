# Product-Recommender-Using-Sentiment-Analysis
This project uses a sentiment analysis model to classify reviews from e-commerce store as negative or positive and then counts the number of positive and negative reviews and then recommends based on the greater count. 
- First sentiment analysis data set is gotten from www.kaggle.com 
- then this data set is trained on the system, the system has an interface to upload data set file
- the data set file has to be formatted according to OpenNLP format
- OpenNLP is use the data set to train a maximum entropy algorithm and produces the en-sentiment3.bin model file
- JSOUP is used to scrap the reviews of the product from the e-commerce website and runs it across the model which determines if its a negative or positive review
