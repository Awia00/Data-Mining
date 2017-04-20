import tensorflow as tf
import keras as ks


hello = tf.constant('Hello, TensorFlow!')
sess = tf.Session()
print(sess.run(hello))