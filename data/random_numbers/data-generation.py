from scipy.stats import uniform, norm
import math, random, sys
import numpy as np
import matplotlib.pyplot as plt
import itertools


def generate_uniform_data(amount, lower_bound, upper_bound):
    read = uniform.rvs(size=amount)
    return np.vectorize(lambda x: lower_bound + math.floor(x * (upper_bound - lower_bound)))(read)


def generate_gaussian_data(amount, mean, sd, lower_bound, upper_bound):
    read = norm(mean, sd).rvs(size=amount)
    return np.vectorize(lambda x: math.floor(max(min(x, upper_bound), lower_bound)))(read)


def generate_uniform_among_gaussian(amount, means, sds, lower_bound, upper_bound):
    if len(means) != len(sds):
        raise ValueError('means and sds should have the same length')

    distribution_amount = int(amount / len(means))

    distributions = list(zip(means, sds))
    result = []

    for dist in distributions:
        result.extend(norm(*dist).rvs(size=distribution_amount))

    result = list(map(int, result))
    random.shuffle(result)

    return result


def write_to_file(filename, setup_data):

    with open(filename, 'w+') as file:
        file.writelines(map(lambda s: str(s) + '\n', setup_data))
    
U_min = -2**31
U_max = 2**31 - 1

if __name__ == '__main__':
    FILES_PER_EXPONENT = 1
    MIN_EXPONENT = 10
    MAX_EXPONENT = 20
    MEAN = 2**28
    SD = 2**25


    QUERY_AMOUNT = int(2**20 / 3)

    for exp in range(MIN_EXPONENT, MAX_EXPONENT + 1):
        print(f'Exponent: {exp : <5}')
        for file_id in range(0, FILES_PER_EXPONENT):
            print(f'  Iteration: {file_id : <2}')
            setup_amount = 2**exp
            setup_numbers = generate_gaussian_data(setup_amount, MEAN, SD, U_min, U_max)

            write_to_file(f'generated-data/n-2^{exp}-{file_id}.data', setup_numbers)