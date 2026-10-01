import pandas as pd

def test_metrics():
    df=pd.DataFrame({'latency_ms':[1,2,3], 'throughput':[10,20,30]})
    assert df.latency_ms.quantile(.5)==2
    assert df.throughput.mean()==20
