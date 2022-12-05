import re
from pathlib import Path

calories = (Path(__file__).parent / 'day1.txt').read_text()

groups = [map(int, g.split()) for g in re.split(r'\n{2,}', calories)]
sums = sorted([sum(g) for g in groups], reverse=True)

print(groups)
print(sums)

print(max(sums))

print(sums[0])

print(sum(sums[:3]))
