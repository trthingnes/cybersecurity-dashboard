// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { type QueryClient } from "@tanstack/react-query";
import { getApiHomeAssistantCoreInfo, getApiHomeAssistantDumpEnv } from "../requests/services.gen";
import * as Common from "./common";
export const prefetchUseGetApiHomeAssistantCoreInfo = (queryClient: QueryClient, clientOptions: Options<unknown, true> = {}) => queryClient.prefetchQuery({ queryKey: Common.UseGetApiHomeAssistantCoreInfoKeyFn(clientOptions), queryFn: () => getApiHomeAssistantCoreInfo({ ...clientOptions }).then(response => response.data) });
export const prefetchUseGetApiHomeAssistantDumpEnv = (queryClient: QueryClient, clientOptions: Options<unknown, true> = {}) => queryClient.prefetchQuery({ queryKey: Common.UseGetApiHomeAssistantDumpEnvKeyFn(clientOptions), queryFn: () => getApiHomeAssistantDumpEnv({ ...clientOptions }).then(response => response.data) });
