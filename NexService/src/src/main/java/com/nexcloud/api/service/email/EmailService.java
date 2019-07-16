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
package com.nexcloud.api.service.email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexcloud.rdb.domain.mysql.Email;
import com.nexcloud.rdb.mapper.mysql.EmailRepository;
import com.nexcloud.util.response.Mysql;
@Service
public class EmailService{
	@Autowired private EmailRepository emailRepository;
	@Autowired private Mysql mysql;

	public boolean addEmail(Email email){
		return emailRepository.addEmail(email);
	}
	public String getEmails(){
		return mysql.resultToResponse(emailRepository.getEmails());
	}
	public boolean updateEmail(Email email) {
		return emailRepository.updateEmail(email);
	}
	public boolean deleteEmail(int emailNo) {
		return emailRepository.deleteEmail(emailNo);
	}
}
