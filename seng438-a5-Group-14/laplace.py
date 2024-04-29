import matplotlib.pyplot as plt

# Function to calculate the Laplace test statistic for each observed failure
def calculate_laplace_test_statistic(theta_j_values):
    # Initialize an empty list to store Laplace test statistics
    laplace_statistics = []
    # Iterate over each observed failure
    for i in range(2, len(theta_j_values) + 1):
        # Select the subset of elapsed testing times up to the current failure
        current_theta_values = theta_j_values[:i]
        # Compute the Laplace test statistic for the subset and append it to the list
        laplace_statistic = compute_laplace_test_statistic(current_theta_values)
        laplace_statistics.append(laplace_statistic)
    return laplace_statistics

# Function to compute the Laplace test statistic for a given set of elapsed testing times
def compute_laplace_test_statistic(theta_j_values):
    # Get the number of observed failures
    num_failures = len(theta_j_values)
    # Check if there are enough observations for the calculation
    if num_failures <= 1:
        raise ValueError("The number of failures should be greater than 1 for the calculation.")

    # Initialize variables for summing terms in the Laplace test statistic formula
    sum_inner = 0
    # Compute the sum of products of elapsed testing times
    for n in range(1, num_failures):
        for j in range(1, n + 1):
            sum_inner += theta_j_values[j - 1]

    # Compute the numerator and denominator of the Laplace test statistic formula
    sum_theta_i = sum(theta_j_values[:num_failures])
    numerator = (1 / (num_failures - 1)) * sum_inner - (sum_theta_i / 2)
    denominator = sum_theta_i * ((1 / (12 * (num_failures - 1))) ** 0.5)

    # Compute the Laplace test statistic
    u_i = numerator / denominator
    return u_i

# Function to read elapsed testing times from a file
def read_elapsed_testing_times_from_file(file_path):
    # Open the file and read elapsed testing times as a list of floats
    with open(file_path, 'r') as file:
        elapsed_testing_times = [float(line.strip()) for line in file.readlines()]
    return elapsed_testing_times

# Function to plot the Laplace test statistic for each observed failure
def plot_laplace_test_statistic(theta_j_values):
    # Calculate Laplace test statistics for each observed failure
    laplace_statistics = calculate_laplace_test_statistic(theta_j_values)
    
    # Plot Laplace test statistics
    plt.figure(figsize=(10, 6))
    plt.plot(range(2, len(theta_j_values) + 1), laplace_statistics, marker='o', linestyle='-', color='b')
    plt.title('Laplace Test Statistic for Each Observed Failure')
    plt.xlabel('Number of Failures (i)')
    plt.ylabel('Laplace Test Statistic (u(i))')
    plt.grid(True)
    plt.show()

# Path to the file containing elapsed testing times
file_path_example = "tbf/report4.txt"
# Read elapsed testing times from the file
elapsed_testing_times = read_elapsed_testing_times_from_file(file_path_example)
# Plot the Laplace test statistic for each observed failure
plot_laplace_test_statistic(elapsed_testing_times)