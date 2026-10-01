from pathlib import Path
import pandas as pd

CSV = Path(__file__).with_name("sample_metrics.csv")
df = pd.read_csv(CSV)
print("events:", len(df))
print("p50 latency (ms):", round(df.latency_ms.quantile(.50), 2))
print("p95 latency (ms):", round(df.latency_ms.quantile(.95), 2))
print("p99 latency (ms):", round(df.latency_ms.quantile(.99), 2))
print("mean throughput:", round(df.throughput.mean(), 2))
print("by event type:\n", df.groupby("event_type").latency_ms.agg(["count","mean","max"]))
