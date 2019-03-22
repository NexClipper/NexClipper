package com.nexcloud.tsdb.client.influx;
/*
* Copyright 2019 NexCloud Co.,Ltd.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {Purpose of This Type}.
 *
 * {Other Notes Relating to This Type (Optional)}
 *
 * @author stefan
 *
 */
// {
// "results": [
// {
// "series": [{}],
// "error": "...."
// }
// ],
// "error": "...."
// }

// {"results":[{"series":[{"name":"cpu","columns":["time","value"],
//           "values":[["2015-06-06T14:55:27.195Z",90],["2015-06-06T14:56:24.556Z",90]]}]}]}
// {"results":[{"series":[{"name":"databases","columns":["name"],"values":[["mydb"]]}]}]}
public class InfluxResult {

  private List<Result> results;
  private String error;

  public List<Map<String, Object>> getTables () {
	  if (this.getResults() == null || 
		  this.getResults().size() == 0) return new ArrayList<>();
	  else if(this.getResults().get(0).series == null || 
		  this.getResults().get(0).series.size() == 0) return new ArrayList<>();
	  List<Map<String, Object>> tables = new ArrayList<>();
	  List<String> columns = this.getResults().get(0).series.get(0).getColumns();
	  List<List<Object>> values = this.getResults().get(0).series.get(0).getValues();
	  for (List<Object> value : values) {
		  Map<String, Object> m = new HashMap<>();
		  for (int i = 0; i < columns.size(); i++) {
			  m.put(columns.get(i), value.get(i));
		  }
		  tables.add(m);
	  }
	  return tables;
  }
  /**
   * @return the results
   */
  public List<Result> getResults() {
    return this.results;
  }

  /**
   * @param results
   *            the results to set
   */
  public void setResults(final List<Result> results) {
    this.results = results;
  }

  /**
   * Checks if this QueryResult has an error message.
   *
   * @return <code>true</code> if there is an error message, <code>false</code> if not.
   */
  public boolean hasError() {
    return this.error != null;
  }

  /**
   * @return the error
   */
  public String getError() {
    return this.error;
  }

  /**
   * @param error
   *            the error to set
   */
  public void setError(final String error) {
    this.error = error;
  }

  public static class Result {
    private List<Series> series;
    private String error;

    /**
     * @return the series
     */
    public List<Series> getSeries() {
      return this.series;
    }

    /**
     * @param series
     *            the series to set
     */
    public void setSeries(final List<Series> series) {
      this.series = series;
    }

    /**
     * Checks if this Result has an error message.
     *
     * @return <code>true</code> if there is an error message,
     *         <code>false</code> if not.
     */
    public boolean hasError() {
      return this.error != null;
    }

    /**
     * @return the error
     */
    public String getError() {
      return this.error;
    }

    /**
     * @param error
     *            the error to set
     */
    public void setError(final String error) {
      this.error = error;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Result [series=");
      builder.append(this.series);
      builder.append(", error=");
      builder.append(this.error);
      builder.append("]");
      return builder.toString();
    }

  }

  public static class Series {
    private String name;
    private Map<String, String> tags;
    private List<String> columns;
    private List<List<Object>> values;

    /**
     * @return the name
     */
    public String getName() {
      return this.name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
      this.name = name;
    }

    /**
     * @return the tags
     */
    public Map<String, String> getTags() {
      return this.tags;
    }

    /**
     * @param tags
     *            the tags to set
     */
    public void setTags(final Map<String, String> tags) {
      this.tags = tags;
    }

    /**
     * @return the columns
     */
    public List<String> getColumns() {
      return this.columns;
    }

    /**
     * @param columns
     *            the columns to set
     */
    public void setColumns(final List<String> columns) {
      this.columns = columns;
    }

    /**
     * @return the values
     */
    public List<List<Object>> getValues() {
      return this.values;
    }

    /**
     * @param values
     *            the values to set
     */
    public void setValues(final List<List<Object>> values) {
      this.values = values;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Series [name=");
      builder.append(this.name);
      builder.append(", tags=");
      builder.append(this.tags);
      builder.append(", columns=");
      builder.append(this.columns);
      builder.append(", values=");
      builder.append(this.values);
      builder.append("]");
      return builder.toString();
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("QueryResult [results=");
    builder.append(this.results);
    builder.append(", error=");
    builder.append(this.error);
    builder.append("]");
    return builder.toString();
  }

}