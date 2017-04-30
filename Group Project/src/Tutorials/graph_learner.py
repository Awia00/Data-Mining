import tensorflow as tf
from src.Tutorials.graph import Graph

graph_size = 5
data_size = graph_size
goal = 4

# hidden layer sizes
n_nodes_hl1 = 250
n_nodes_hl2 = 175
n_nodes_hl3 = 100

# classes is number of output neurons

# height by width
input_start_vertex = tf.placeholder('float', [None, graph_size * graph_size])
input_graph = tf.placeholder('float', [None, graph_size * graph_size])
output = tf.placeholder('float')


def build_data():
    graph = Graph(graph_size)
    graph.add_edge(0, 1)
    graph.add_edge(1, 3)
    graph.add_edge(2, 4)
    graph.add_edge(4, 3)

    input_start_data = []
    input_graph_data = []
    output_data = []
    for start in range(data_size):
        if graph.shortest_path(start, goal) is not None:
            input_start_data.append(vertex_to_one_hot(start, graph_size))
            input_graph_data.append(vertex_to_one_hot(goal, graph_size))
            output_data.append(vertex_to_one_hot(graph.next_on_shortest_path(start, goal), graph_size))

    return input_start_data, input_graph_data, output_data


def vertex_to_one_hot(vertex, size):
    result = []
    for index in range(size):
        if index == vertex:
            result.append(1)
        else:
            result.append(0)


def neural_network_model(input_data):
    hidden_1_layer = {
        'weights': tf.Variable(tf.random_normal([graph_size*graph_size, n_nodes_hl1])),
        'biases': tf.Variable(tf.random_normal([n_nodes_hl1]))
    }
    hidden_2_layer = {
        'weights': tf.Variable(tf.random_normal([n_nodes_hl1, n_nodes_hl2])),
        'biases': tf.Variable(tf.random_normal([n_nodes_hl2]))
    }
    hidden_3_layer = {
        'weights': tf.Variable(tf.random_normal([n_nodes_hl2, n_nodes_hl3])),
        'biases': tf.Variable(tf.random_normal([n_nodes_hl3]))
    }
    output_layer = {
        'weights': tf.Variable(tf.random_normal([n_nodes_hl3, graph_size])),
        'biases': tf.Variable(tf.random_normal([graph_size]))
    }

    # input data * weights + biases
    l1 = tf.add(tf.matmul(input_data, hidden_1_layer['weights']), hidden_1_layer['biases'])
    l1 = tf.nn.relu(l1)

    l2 = tf.add(tf.matmul(l1, hidden_2_layer['weights']), hidden_2_layer['biases'])
    l2 = tf.nn.relu(l2)

    l3 = tf.add(tf.matmul(l2, hidden_3_layer['weights']), hidden_3_layer['biases'])
    l3 = tf.nn.relu(l3)

    lo = tf.matmul(l3, output_layer['weights']) + output_layer['biases']

    return lo


def train_neural_network(input_x):
    prediction = neural_network_model(input_x[0])
    cost = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits=prediction, labels=y))
    optimizer = tf.train.AdamOptimizer().minimize(cost)

    hm_epochs = 1000

    with tf.Session() as sess:
        sess.run(tf.global_variables_initializer())

        for epoch in range(hm_epochs):
            epoch_loss = 0

            # feed forward
            epoch_x, epoch_y = data[0][epoch % data_size], data[1][epoch % data_size]
            # back prop
            _, c = sess.run([optimizer, cost], feed_dict={x: epoch_x, y: epoch_y})
            # error stuff?
            epoch_loss += c
            print('Epoch', epoch, 'completed out of', hm_epochs, 'loss:', epoch_loss)

        # statistics?
        correct = tf.equal(tf.argmax(prediction, 1), tf.argmax(y, 1))
        accuracy = tf.reduce_mean(tf.cast(correct, 'float'))
        print('Accuracy:', accuracy.eval({input_start_vertex: data[0], input_graph_vertex: data[1], output: data[2]}))


data = build_data()
train_neural_network()
