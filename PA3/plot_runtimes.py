import pandas as pd
import matplotlib.pyplot as plt
from matplotlib.ticker import FuncFormatter

data = pd.read_csv('sort_runtimes.csv')

sorts = [
    'Bucket Sort',
    'Count Sort',
]

plt.rcParams['font.family'] = 'DejaVu Sans'
fig, axs = plt.subplots(2, 2, figsize=(12, 8))
fig.suptitle('Sorting Algorithms Performance', fontsize=16)
fig.set_facecolor('#E2DAD1')

for i, sort_type in enumerate(sorts):
    row, col = divmod(i, 2) 
    ax = axs[row, col]
    subset = data[data['sort_type'] == sort_type]

    run_time_ms = subset['run_time'] / 1_000_000
    ax.scatter(subset['data_size'], run_time_ms, label=sort_type, color='#251605', marker='o', s=25, alpha=0.8)

    ax.xaxis.set_major_formatter(FuncFormatter(lambda x, _: "{:,.0f}".format(x)))
    ax.set_title(sort_type, fontsize=14)
    ax.tick_params(axis='x', labelsize=10)
    ax.set_xlabel('Data Size', fontsize=12)
    ax.set_ylabel('Run Time (ms)', fontsize=12)
    ax.set_facecolor('#F1F1F0')

plt.tight_layout(rect=[0, 0.03, 1, 0.95])
plt.subplots_adjust(wspace=0.3, hspace=0.6)

plt.show()